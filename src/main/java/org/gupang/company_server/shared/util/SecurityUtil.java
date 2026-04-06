package org.gupang.company_server.shared.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.gupang.common.exception.CustomException;
import org.gupang.common.exception.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtil {
    public static Optional<UserDetails> getCurrentUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(principal -> principal instanceof UserDetails)
                .map(UserDetails.class::cast);
    }

    public static Optional<String> getCurrentUserId() {
        return getCurrentUser().map(UserDetails::getUsername);
    }

    public static String getCurrentUserIdOrThrow() {
        return getCurrentUserId().orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED));
    }

    public static Optional<String> getCurrentUsername() {
        return getCurrentUser().map(UserDetails::getUsername);
    }
}
