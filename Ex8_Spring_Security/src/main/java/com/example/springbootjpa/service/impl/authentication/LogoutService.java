package com.example.springbootjpa.service.impl.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {

  @Override
  public void logout(
          HttpServletRequest request,
          HttpServletResponse response,
          Authentication authentication
  ) {
    final String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      // Xoá thông tin token trong mã nguồn hoặc xoá cookie/session chứa token
      // Ví dụ:
      // token = null; // hoặc
       request.getSession().removeAttribute("token");
//       response.removeCookie("token");
      SecurityContextHolder.clearContext();
    }
  }
}
