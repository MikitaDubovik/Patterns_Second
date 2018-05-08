package sample.FactoryMethod;

public class CsvFactory extends Factory {
    @Override
    public Parser CreateParser() {
        return new CsvParser();
    }
}
