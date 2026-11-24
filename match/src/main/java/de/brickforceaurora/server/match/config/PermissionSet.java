package de.brickforceaurora.server.match.config;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;

public final class PermissionSet {

    private final ObjectSet<String> permissions = ObjectSets.synchronize(new ObjectArraySet<>());
    private final AtomicBoolean dirty = new AtomicBoolean();
    
    private final UUID owner;

    public PermissionSet(UUID owner) {
        this.owner = owner;
    }
    
    public UUID owner() {
        return owner;
    }
    
    public boolean has(String permission) {
        return permissions.contains(permission);
    }

    public void set(boolean allowed, String... permissions) {
        for (String permission : permissions) {
            set(allowed, permission);
        }
    }

    public void set(boolean allowed, String permission) {
        if (permissions.contains(permission) == allowed) {
            return;
        }
        try {
            if (allowed) {
                permissions.add(permission);
                return;
            }
            permissions.remove(permission);
        } finally {
            setDirty();
        }
    }

    public void clear() {
        try {
            permissions.clear();
        } finally {
            setDirty();
        }
    }

    public boolean isEmpty() {
        return permissions.isEmpty();
    }

    /*
     * 
     */

    boolean isDirty() {
        return dirty.get();
    }

    void overwrite(List<String> permissions) {
        try {
            this.permissions.clear();
            this.permissions.addAll(permissions);
        } finally {
            unsetDirty();
        }
    }

    List<String> asList() {
        try {
            if (permissions.isEmpty()) {
                return null;
            }
            return new ObjectArrayList<>(permissions);
        } finally {
            unsetDirty();
        }
    }

    private void setDirty() {
        dirty.compareAndSet(false, true);
    }

    private void unsetDirty() {
        dirty.compareAndSet(true, false);
    }

}
