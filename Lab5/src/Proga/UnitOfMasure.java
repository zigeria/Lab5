package Proga;

/**
 * Единицы измерения продукта
 */
public enum UnitOfMasure{
    CENTIMETERS("Сантиметры"),
    MILLILITERS("Миллилитры"),
    MILLIGRAMS("Миллиграммы");

    private final String unitOfMasure;

    UnitOfMasure(String unitOfMasure) {
        this.unitOfMasure = unitOfMasure;
    }

    @Override
    public String toString() {
        return unitOfMasure;
    }

    public static UnitOfMasure getUnitOfMasure(String unitOfMasure_str) throws IllegalArgumentException{
        for(UnitOfMasure unitOfMasure : UnitOfMasure.values()){
            if(unitOfMasure.toString().equals(unitOfMasure_str)){
                return unitOfMasure;
            }
        }
        throw new IllegalArgumentException();
    }
}
