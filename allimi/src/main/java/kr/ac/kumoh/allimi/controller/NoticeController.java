package kr.ac.kumoh.allimi.controller;

import kr.ac.kumoh.allimi.dto.notice.NoticeEditDto;
import kr.ac.kumoh.allimi.dto.notice.NoticeListDTO;
import kr.ac.kumoh.allimi.dto.notice.NoticeResponse;
import kr.ac.kumoh.allimi.dto.notice.NoticeWriteDto;
import kr.ac.kumoh.allimi.exception.FacilityException;
import kr.ac.kumoh.allimi.exception.NHResidentException;
import kr.ac.kumoh.allimi.exception.user.UserAuthException;
import kr.ac.kumoh.allimi.exception.user.UserException;
import kr.ac.kumoh.allimi.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NoticeController { 
  private final NoticeService noticeService;
  
  // 알림장 작성 
  @PostMapping(value = "/v2/notices")  // notice{user_id, nhresident_id, facility_id, contents, sub_contents}, file{}
  public ResponseEntity noticeWrite(@RequestPart(value="notice") NoticeWriteDto dto,
                                    @RequestPart(value="file", required = false) List<MultipartFile> files) {

    if (dto.getUser_id() == null || dto.getNhresident_id() == null || dto.getFacility_id() == null) {
      log.info("NoticeController 알림장 작성: 필요한  값이 제대로 안들어옴. 사용자의 잘못된 입력");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    try {
      noticeService.write(dto, files);
    } catch (UserAuthException e) { //알림장 쓸 권한 없음
      log.info("NoticeController 알림장 작성: 권한이 없는 사용자");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } catch (UserException |NHResidentException |FacilityException e) { //알림장 쓰기 실패
      log.info("NoticeController 알림장 작성: user, resident, facility 중 하나 찾기 실패");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } catch (Exception e) { //알림장 쓰기 실패
      log.info("NoticeController 알림장 작성: 알림장 쓰기 실패");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/v2/notices/{resident_id}") // 알림장 목록
  public ResponseEntity noticeList(@PathVariable("resident_id") Long residentId) {
    List<NoticeListDTO> noticeList;

    try {
      noticeList = noticeService.noticeList(residentId);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    return ResponseEntity.status(HttpStatus.OK).body(noticeList);
  }

  @GetMapping("/v2/notices/detail/{notice_id}") // 알림장 상세보기
  public ResponseEntity noticeDetail(@PathVariable("notice_id") Long noticeId) {
    NoticeResponse noticeResponse;

    if (noticeId == null)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    try {
      noticeResponse = noticeService.getDetail(noticeId);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(noticeResponse);
  }

  @PatchMapping("/v2/notices") // 알림장 수정
  public ResponseEntity noticeEdit(@RequestPart(value = "notice") NoticeEditDto dto,
                                   @RequestPart(value = "file", required = false) List<MultipartFile> files) {
    try {
      noticeService.edit(dto, files);
    } catch (Exception e) {
      log.info("error: {}", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/v2/notices") // 알림장 삭제
  public ResponseEntity noticeDelete(@RequestBody Map<String, Long> notice) {
    Long noticeId = notice.get("notice_id");

    if (noticeId == null)
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

    Long deletedCnt = noticeService.delete(notice.get("notice_id"));

    if (deletedCnt == 0)
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}


