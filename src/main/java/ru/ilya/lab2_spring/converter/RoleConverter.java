package ru.ilya.lab2_spring.converter;

//@Converter(autoApply = true)
//public class RoleConverter implements AttributeConverter<Role, Integer> {

//    @Override
//    public Integer convertToDatabaseColumn(Role role) {
//        if (role == null) {
//            return null;
//        }
//        return role.getCode();
//    }
//
//    @Override
//    public Role convertToEntityAttribute(Integer code) {
//        if (code == null) {
//            return null;
//        }
//        return Role.fromCode(code);
//    }
//}