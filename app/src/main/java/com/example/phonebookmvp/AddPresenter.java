package com.example.phonebookmvp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.ContactsContract;

public class AddPresenter {
    private AddInterface addInterface;

    public AddPresenter(AddInterface addInterface) {
        this.addInterface = addInterface;
    }

    public void saveContact(String addname, String addnumber, MainActivity mMainActivity) {
        Uri addContactsUri = ContactsContract.Data.CONTENT_URI;
        long rowContactId = getRawContactId(mMainActivity);

        insertContactDisplayName(addContactsUri, rowContactId, addname, mMainActivity);
        insertContactPhoneNumber(addContactsUri, rowContactId, addnumber, mMainActivity);

        addInterface.getSuccess();

    }

    private void insertContactPhoneNumber(Uri addContactsUri, long rowContactId, String addnumber, MainActivity mMainActivity) {
        // Create a ContentValues object.
        ContentValues contentValues = new ContentValues();

        // Each contact must has an id to avoid java.lang.IllegalArgumentException: raw_contact_id is required error.
        contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, rowContactId);

        // Each contact must has an mime type to avoid java.lang.IllegalArgumentException: mimetype is required error.
        contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);

        // Put phone number value.
        contentValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER, addnumber);

        // Calculate phone type by user selection.
        int phoneContactType = ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;

        // Put phone type value.
        contentValues.put(ContactsContract.CommonDataKinds.Phone.TYPE, phoneContactType);

        // Insert new contact data into phone contact list.
        mMainActivity.getContentResolver().insert(addContactsUri, contentValues);
    }

    private void insertContactDisplayName(Uri addContactsUri, long rowContactId, String addname, MainActivity mMainActivity) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, rowContactId);

        // Each contact must has an mime type to avoid java.lang.IllegalArgumentException: mimetype is required error.
        contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);

        // Put contact display name value.
        contentValues.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, addname);

        mMainActivity.getContentResolver().insert(addContactsUri, contentValues);
    }

    private long getRawContactId(MainActivity mMainActivity) {
        ContentValues contentValues = new ContentValues();
        Uri rawContactUri = mMainActivity.getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, contentValues);
        // Get the newly created contact raw id.
        long ret = ContentUris.parseId(rawContactUri);
        return ret;
    }
}
