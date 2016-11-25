package io.sphere.sdk.annotations.processors;

import io.sphere.sdk.annotations.ResourceDraftValue;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes({"io.sphere.sdk.annotations.ResourceDraftValue"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public final class ResourceDraftValueAnnotationProcessor extends CommercetoolsAnnotationProcessor<ResourceDraftValue> {

    public ResourceDraftValueAnnotationProcessor() {
        super(ResourceDraftValue.class);
    }

    @Override
    protected void generate(final TypeElement typeElement) {
        writeClass(typeElement, new ResourceDraftDslClassModelFactory(typeElement).createClassModel());
        writeClass(typeElement, new ResourceDraftBuilderClassModelFactory(typeElement).createClassModel());
    }
}