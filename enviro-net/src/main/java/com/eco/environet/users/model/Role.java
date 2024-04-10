package com.eco.environet.users.model;

public enum Role {
    ADMINISTRATOR,
    REGISTERED_USER,
    ACCOUNTANT,
    BOARD_MEMBER,
    PROJECT_MANAGER,
    PROJECT_COORDINATOR,
    EDUCATOR;

    public boolean isOrganizationMember() {
        Role[] organizationRoles = {ACCOUNTANT, BOARD_MEMBER, PROJECT_MANAGER, PROJECT_COORDINATOR, EDUCATOR};
        for (Role role : organizationRoles) {
            if (this == role) {
                return true;
            }
        }
        return false;
    }
}
