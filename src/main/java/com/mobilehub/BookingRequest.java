package com.mobilehub;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BookingRequest {
    @NotBlank @Size(min = 2, max = 80)
    private String fullName;

    @NotBlank @Pattern(regexp = "^[0-9+() .-]{8,20}$", message = "Enter a valid contact number")
    private String phoneNumber;

    @NotBlank @Size(max = 80)
    private String city;

    @NotBlank
    private String contactMethod;

    @Size(max = 500)
    private String notes;

    private boolean consent;

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getContactMethod() { return contactMethod; }
    public void setContactMethod(String contactMethod) { this.contactMethod = contactMethod; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public boolean isConsent() { return consent; }
    public void setConsent(boolean consent) { this.consent = consent; }
}
