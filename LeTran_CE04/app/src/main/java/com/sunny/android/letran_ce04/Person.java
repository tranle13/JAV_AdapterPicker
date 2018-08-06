
    // Name: Tran Le
    // JAV1 - 1808
    // File name: Person.java

package com.sunny.android.letran_ce04;

    import android.media.Image;

    class Person {

    // Member variables
    private String pFirstName = "";
    private String pLastName = "";
    private String pBirthday = "";
    private int pAvatar = 0;

    // Constructor
    Person(String _firstName, String _lastName, String _birthday, int _avatar) {
        pFirstName = _firstName;
        pLastName = _lastName;
        pBirthday = _birthday;
        pAvatar = _avatar;
    }

    String getFullName() { return pFirstName + pLastName; }

    String getBirthday() { return pBirthday; }

    int getImageID() { return pAvatar; }

    public String toString() {
        return pFirstName + " " + pLastName;
    }
}
