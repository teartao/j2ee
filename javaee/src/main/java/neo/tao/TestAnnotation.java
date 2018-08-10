package neo.tao;

import neo.tao.annotation.*;

@TypeAnnotation //ElementType.TYPE
public class TestAnnotation {

    @FieldAnnotation //ElementType.FIELD
    private String field;

    @ConstructorAnnotation //ElementType.CONSTRUCTOR
    public TestAnnotation() {

    }

    @MethodAnnotation //ElementType.METHOD
    public String testMethod(@ParameterAnnotation String parameter, //ElementType.TYPE
                             String anotherParameter) {
        @LocalVariableAnnotation String localVariable; //ElementType.LOCAL_VARIABLE
        return parameter;
    }
}
