package org.androidtown.test.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Stream;

public class AddressListModel extends ArrayList<AddressListModel> implements Serializable {

    private String addressDefault;
    private String userName;
    private String userAddress;
    private String userContact;
    private String userEmail;
    private String userNote;
    private Boolean isSelected;

    public AddressListModel(String addressDefault, String name, String address, String contact, String email, String note) {
        this.addressDefault = addressDefault;
        this.userName = name;
        this.userAddress = address;
        this.userContact = contact;
        this.userEmail = email;
        this.userNote = note;
    }

    public String getDefault() { return this.addressDefault; }
    public void setDefault(String addressDefault) { this.addressDefault = addressDefault; }

    public String getUserName() { return this.userName; }
    public void setUserName(String name) { this.userName = name; }

    public String getUserAddress() { return this.userAddress; }
    public void setUserAddress(String address) { this.userAddress = address; }

    public String getUserContact() { return this.userContact; }
    public void setUserContact(String contact) { this.userContact = contact; }

    public String getUserEmail() { return this.userEmail; }
    public void setUserEmail(String email) { this.userEmail = email; }

    public String getUserNote() { return this.userNote; }
    public void setUserNote(String note) { this.userName = note; }

    public Boolean getIsSelected() { return this.isSelected; }
    public void setIsSelected(Boolean B) { this.isSelected = B; }
}
