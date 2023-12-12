package com.cliin.cliinbn2.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class CliinMemberController {

    private final CliinMemberService cliinMemberService;
    private final SessionManager sessionManager;

    @PostMapping("/signup")
    public User signUp(@RequestBody CliinSignupDto cliinSignupDto) {
        return cliinMemberService.signup(cliinSignupDto);
    }

    @PostMapping("/login")
    public User login(@RequestBody CliinLoginDto cliinLoginDto, HttpSession session) {
        User loggedInUser = cliinMemberService.login(cliinLoginDto);

        if (loggedInUser != null) {
            session.setAttribute("userId", loggedInUser.getUserId());
        }

        return loggedInUser;
    }

    @GetMapping("/logineduser")
    public String loginedUser() {
        String userId = sessionManager.getUserIdFromSession();

        return userId != null ? userId : "User ID not found in session";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            redirectAttributes.addFlashAttribute("logoutMessage", "Logged out successfully");
        }
        return "redirect:/";
    }

    @PostMapping("/missionUpload")
    public Mission missionUpload(@RequestPart("multipartFile") MultipartFile multipartFile,
                                 @RequestPart("title") String title,
                                 @RequestPart("hashtag") String hashtag,
                                 @RequestPart("content") String content) {

        String userId = sessionManager.getUserIdFromSession();
        CliinMissionDto missionDto = new CliinMissionDto();
        missionDto.setTitle(title);
        missionDto.setHashtag(hashtag);
        missionDto.setContents(content);
        missionDto.setMultipartFile(multipartFile);

        return cliinMemberService.upload(missionDto, userId);
    }

    @GetMapping("/missionboard")
    public List<Mission> missionBoard() {
        return cliinMemberService.getAllMissions();
    }

    @PostMapping("/articleUpload")
    public Article articleUpload(@RequestPart("multipartFile") MultipartFile multipartFile,
                                 @RequestPart("title") String title,
                                 @RequestPart("subtitle") String subtitle,
                                 @RequestPart("hashtag") String hashtag,
                                 @RequestPart("content") String content) {

        String userId = sessionManager.getUserIdFromSession();
        CliinArticleDto articleDto = new CliinArticleDto();
        articleDto.setTitle(title);
        articleDto.setSubtitle(subtitle);
        articleDto.setHashtag(hashtag);
        articleDto.setContent(content);
        articleDto.setMultipartFile(multipartFile);

        return cliinMemberService.articleUpload(articleDto, userId);
    }

    @GetMapping("/scriptboard")
    public List<Article> articleBoard() {
        return cliinMemberService.getAllArticles();
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<CliinProfileDto> profile(@PathVariable("userId") String userId) {
        CliinProfileDto cliinProfileDto = cliinMemberService.getProfile(userId);
        return ResponseEntity.ok(cliinProfileDto);
    }

}
