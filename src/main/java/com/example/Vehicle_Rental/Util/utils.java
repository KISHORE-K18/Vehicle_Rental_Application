package com.example.Vehicle_Rental.Util;

import com.example.Vehicle_Rental.auth.UserPrincipal;
import com.example.Vehicle_Rental.model.User;

import java.util.UUID;

public final class utils {

    public static UUID parseUserId(UserPrincipal principal)
    {
        User user = principal.getUser();
        return user.getId();
    }
}
