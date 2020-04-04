package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void update() {
        storage.update(UUID_2);
        Assert.assertEquals(UUID_2, storage.get(UUID_2).getUuid());
    }

    @Test
    public void save() {
        storage.save(new Resume("uuid4"));
        Assert.assertEquals("uuid4", storage.get("uuid4").getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        Assert.assertNull(storage.get(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getAll() {
        Assert.assertEquals(3, storage.size());
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size());
        Assert.assertNull(storage.get(UUID_2));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals("uuid1", storage.get(UUID_1).getUuid());
        Assert.assertEquals("uuid2", storage.get(UUID_2).getUuid());
        Assert.assertEquals("uuid3", storage.get(UUID_3).getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("7");
    }

    @Test(expected = StorageException.class)
    public void getOverflow() {
        Resume[] resumes = storage.getStorage();
        for (int i = storage.size(); i < resumes.length; i++) {
            try {
                storage.save(new Resume());
            } catch (Exception e) {
                Assert.fail("Ошибка до заполнения массива!");
            }
        }
        storage.save(new Resume());
    }
}