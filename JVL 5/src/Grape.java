class Grape {
    private String variety;
    private GrapeColor color;
    private StorageDuration storage;
    private GrapeType type;
    private double price;

    public Grape(String variety, GrapeColor color, StorageDuration storage, GrapeType type, double price) {
        this.variety = variety;
        this.color = color;
        this.storage = storage;
        this.type = type;
        this.price = price;
    }

    // Геттеры
    public String getVariety() { return variety; }
    public GrapeColor getColor() { return color; }
    public StorageDuration getStorage() { return storage; }
    public GrapeType getType() { return type; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("Сорт: %-10s Цвет: %-6s Хранение: %-10s Тип: %-8s Цена: %.2f",
                variety, color, storage, type, price);
    }
}
