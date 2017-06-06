package com.slanf.reference.processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Created by Song on 2017/6/5.
 */
@SupportedAnnotationTypes({"com.slanf.reference.annotation.Bind"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class BindAnnoProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for(TypeElement typeElement:annotations){
            for(Element element:roundEnv.getElementsAnnotatedWith(typeElement)){
                System.out.println(element);
            }
        }
        return false;
    }
}
