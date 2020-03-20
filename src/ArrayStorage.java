/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int size = size();
        if (size != 0) {
            for (int i = 0; i < storage.length; i++) {
                if (size == 0) {
                    break;
                }
                if (storage[i] != null) {
                    storage[i] = null;
                    size--;
                }
            }
        }
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
        Resume[] arrayWithoutNull = new Resume[size()];
        if (arrayWithoutNull.length == 0) {
            return arrayWithoutNull;
        }
        int position = 0;
        for (Resume r : storage) {
            if (r != null) {
                arrayWithoutNull[position++] = r;
            }
        }
        return arrayWithoutNull;
    }

    int size() {
        int size = 0;
        for (Resume r : storage) {
            if (r != null) size++;
        }
        return size;
    }
}
