package io.sphere.sdk.zones.expansion;

import io.sphere.sdk.expansion.ExpansionModel;
import io.sphere.sdk.expansion.ExpansionPath;

import java.util.List;

final class ZoneExpansionModelImpl<T> extends ExpansionModel<T> implements ZoneExpansionModel<T> {
    ZoneExpansionModelImpl(final List<String> parentPath, final String path) {
        super(parentPath, path);
    }

    ZoneExpansionModelImpl() {
        super();
    }

    @Override
    public List<ExpansionPath<T>> expansionPaths() {
        return buildExpansionPaths();
    }
}
