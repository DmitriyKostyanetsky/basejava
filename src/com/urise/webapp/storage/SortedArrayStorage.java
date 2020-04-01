package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveElement(Resume resume, int index) {
        int destPos = -index - 1;
        System.arraycopy(storage, destPos, storage, destPos + 1, size - destPos);
        storage[destPos] = resume;
    }

    @Override
    protected void deleteElement(String uuid, int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index);
    }

}
