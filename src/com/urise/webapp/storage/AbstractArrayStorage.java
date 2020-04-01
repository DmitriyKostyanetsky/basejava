package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void update(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with id " + uuid + " is not present in storage.");
        } else if (!storage[index].getUuid().equals(uuid)) {
            System.out.println("Resumes are not equals!");
        } else {
            storage[index].setUuid(uuid);
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            System.out.println("Resume with id " + resume.getUuid() + " is not present in storage.");
        } else if (size == storage.length) {
            System.out.println("Storage of resumes is full!");
        } else {
            saveElement(resume, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteElement(uuid, index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume with id " + uuid + " is not present in storage.");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
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

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with id " + uuid + " is not present in storage.");
            return null;
        }
        System.out.println("Resume with id " + uuid + " is present in storage.");
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveElement(Resume resume, int index);

    protected abstract void deleteElement(String uuid, int index);

}
