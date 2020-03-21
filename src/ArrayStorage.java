/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (size() != 0) {
            for (Resume asResume : storage) {
                if (asResume != null && asResume.toString().equals(r.toString())) {
                    return;
                }
            }
        }
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                size++;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume r : storage) {
            if (r != null && r.toString().equals(uuid)) {
                return r;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].toString().equals(uuid)) {
                storage[i] = null;
                size--;
                break;
            }
        }
        Resume[] temp = new Resume[storage.length];
        int position = 0;
        for (int i = 0; i < temp.length; i++) {
            if (storage[i] != null) {
                temp[position++] = storage[i];
            }
        }
        storage = temp;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size()];
        if (resumes.length == 0) {
            return resumes;
        }
        int position = 0;
        for (Resume r : storage) {
            if (r != null) {
                resumes[position++] = r;
            }
        }
        return resumes;
    }

    int size() {
        return size;
    }
}
