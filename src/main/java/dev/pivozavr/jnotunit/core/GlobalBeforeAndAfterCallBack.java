package dev.pivozavr.jnotunit.core;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public interface GlobalBeforeAndAfterCallBack extends BeforeAllCallback {

    @Override
    default void beforeAll(ExtensionContext context) {
        context.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).
                getOrComputeIfAbsent(this.getClass(),
                        k -> {
                            beforeAllTests();
                            return (ExtensionContext.Store.CloseableResource) this::afterAllTests;
                        }
                );
    }

    public void beforeAllTests();

    public void afterAllTests();
}
