package com.cliin.cliinbn2.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/missionupload")
    public Mission missionUpload(@RequestBody CliinMissionDto cliinMissionDto) {
        return cliinMemberService.upload(cliinMissionDto);
    }

}
