package ru.ilya.lab2_spring.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.ilya.lab2_spring.model.enums.Category;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Category category) {
        if (category == null) {
            return null;
        }
        return category.getCode();
    }

    @Override
    public Category convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return Category.fromCode(code);
    }
}