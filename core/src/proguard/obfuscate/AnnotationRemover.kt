package proguard.obfuscate

import proguard.classfile.Clazz
import proguard.classfile.attribute.Attribute
import proguard.classfile.attribute.annotation.AnnotationsAttribute
import proguard.classfile.attribute.visitor.AttributeVisitor
import proguard.classfile.util.ClassUtil
import proguard.classfile.util.SimplifiedVisitor

class AnnotationRemover(annotationName: String) : SimplifiedVisitor(), AttributeVisitor {
    private val annotationName = "L${ClassUtil.internalClassName(annotationName)};"
    override fun visitAnyAttribute(clazz: Clazz, attribute: Attribute) {
    }

    override fun visitAnyAnnotationsAttribute(clazz: Clazz, annotationsAttribute: AnnotationsAttribute) {
        annotationsAttribute.annotations = annotationsAttribute.annotations
            .filter {
                it.getType(clazz) != annotationName
            }
            .toTypedArray()

        annotationsAttribute.u2annotationsCount = annotationsAttribute.annotations.size
    }
}