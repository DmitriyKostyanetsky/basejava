package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size;
    private int position;

    public void update(String uuid) {
        if (get(uuid) != null) {
            storage[position].setUuid(uuid);
        }
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (get(resume.getUuid()) == null && size != storage.length) {
            storage[size++] = resume;
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.out.println("Resume with id " + uuid + " is present in storage.");
                position = i;
                return storage[i];
            }
        }
        System.out.println("Resume with id " + uuid + " is not present in storage.");
        return null;
    }

    public void delete(String uuid) {
        if (get(uuid) != null) {
            storage[position] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    public int size() {
        return size;
    }
}
