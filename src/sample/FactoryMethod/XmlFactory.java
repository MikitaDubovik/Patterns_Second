package sample.FactoryMethod;

public class XmlFactory extends Factory {
    @Override
    public Parser CreateParser() {
        return new XmlParser();
    }
}
