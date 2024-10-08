Jackson — это популярная библиотека для работы с JSON в Java, которая позволяет конвертировать объекты Java в формат JSON и обратно. Jackson предоставляет гибкий и мощный API для обработки JSON-данных и сериализации/десериализации объектов. Основные абстракции и классы Jackson позволяют эффективно работать с JSON, и их можно разбить на несколько ключевых компонентов.

1. ObjectMapper
ObjectMapper — это основной класс в Jackson для работы с JSON. Он используется для:
Преобразования объектов Java в JSON (сериализация).
Преобразования JSON в объекты Java (десериализация).
Выполнения сложных операций, таких как форматирование JSON, управление поведением сериализации и десериализации.

ObjectMapper objectMapper = new ObjectMapper();

// Преобразование объекта в JSON
String json = objectMapper.writeValueAsString(someObject);

// Преобразование JSON в объект
SomeClass someObject = objectMapper.readValue(jsonString, SomeClass.class);

2. JsonParser
JsonParser — это класс для побуквенного (streaming) анализа JSON-данных. С его помощью можно последовательно читать данные из JSON-документа и обрабатывать их.
JsonParser позволяет снизить объем памяти при работе с большими JSON-файлами, так как работает с данными по мере их поступления.

JsonFactory factory = new JsonFactory();
JsonParser parser = factory.createParser(jsonInput);

while (parser.nextToken() != JsonToken.END_OBJECT) {
    // Обработка каждого токена
}
parser.close();
3. JsonGenerator
JsonGenerator — это класс для потоковой записи JSON. Он позволяет записывать JSON данные поэтапно, что полезно при создании больших JSON-файлов или поточных операций.
Этот класс может создавать JSON-объекты, массивы, записывать значения и так далее.
  
JsonFactory factory = new JsonFactory();
JsonGenerator generator = factory.createGenerator(new FileWriter("output.json"));

generator.writeStartObject();
generator.writeStringField("name", "John");
generator.writeNumberField("age", 30);
generator.writeEndObject();
generator.close();

4. JsonNode
JsonNode представляет собой узел в иерархическом представлении JSON. Этот класс является частью дерева, которое Jackson может создавать при парсинге JSON-данных. Он удобен для работы с неизвестной или динамической структурой JSON.
ObjectMapper позволяет получить JSON в виде дерева JsonNode.

JsonNode rootNode = objectMapper.readTree(jsonString);
String name = rootNode.path("name").asText();
int age = rootNode.path("age").asInt();

5. Annotation-based Configuration
Jackson поддерживает аннотации для настройки сериализации и десериализации. Наиболее популярные аннотации:
@JsonProperty — указывает имя свойства в JSON.
@JsonIgnore — исключает поле из JSON.
@JsonInclude — управляет условиями включения свойства.
@JsonFormat — указывает форматирование данных (например, даты).

public class Person {
    @JsonProperty("full_name")
    private String name;
    
    @JsonIgnore
    private int age;
}

6. Module
Jackson предоставляет гибкий механизм добавления модулей для расширения возможностей работы с JSON. Это могут быть модули для поддержки других типов данных, например, Java 8 типов (Optional, LocalDate и т. д.), библиотеки Joda-Time, JavaFX и так далее.
Подключение модуля позволяет поддерживать сложные или специфические типы данных без дополнительного кода.

ObjectMapper objectMapper = new ObjectMapper();
objectMapper.registerModule(new JavaTimeModule());

7. DeserializationContext и SerializationContext
Эти классы используются при написании пользовательских сериализаторов и десериализаторов. Они предоставляют методы и контекст для сериализации/десериализации нестандартных или сложных объектов.
DeserializationContext используется для получения доступа к текущему состоянию и параметрам при преобразовании JSON в объект.
SerializationContext используется для управления процессом обратного преобразования объекта в JSON.
  
8. Custom Serializer и Deserializer
Jackson позволяет создавать собственные сериализаторы и десериализаторы для управления конвертацией нестандартных типов.

public class CustomDateDeserializer extends JsonDeserializer<Date> {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) 
            throws IOException {
        String date = p.getText();
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

9. Tree Traversing Parser и Generator
Jackson также предоставляет классы для обработки JSON в виде дерева, что позволяет программно обходить его, получать узлы и создавать более сложные структуры данных. Tree Traversing Parser и Tree Traversing Generator обеспечивают возможность обхода узлов и модификации дерева.
