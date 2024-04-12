package com.eco.environet.users.model;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ADMINISTRATOR,
    REGISTERED_USER,
    ACCOUNTANT,
    BOARD_MEMBER,
    PROJECT_MANAGER,
    PROJECT_COORDINATOR,
    EDUCATOR;

    public boolean isOrganizationMember() {
        List<Role> organizationRoles = Arrays.asList(ACCOUNTANT, BOARD_MEMBER, PROJECT_MANAGER, PROJECT_COORDINATOR, EDUCATOR);
        return organizationRoles.contains(this);
    }

    public static List<Role> getAllOrganizationRoles() {
        return Arrays.asList(ACCOUNTANT, BOARD_MEMBER, PROJECT_MANAGER, PROJECT_COORDINATOR, EDUCATOR);
    }
}
