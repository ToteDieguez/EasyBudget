package com.easybudget.category.type;

public enum CategoryType {

    INCOME,
    SAVING,
    EXPENSE;

    public static CategoryType getCategoryType(String type) {
        for (CategoryType categoryType : CategoryType.values()) {
            if (categoryType.toString().equals(type)) {
                return categoryType;
            }
        }
        throw new IllegalArgumentException("No Category Type Found.");
    }
}
