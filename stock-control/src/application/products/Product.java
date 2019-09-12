package application.products;

import application.addProduct.AddProductEnum;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * The Product class represents products within the application.
 *
 * @author David Spencer
 * @version 1.0
 */
public class Product
{
    private static final String DEFAULT_IMAGE =
            "https://www.bulkbarn.ca/app_themes/BulkBarn/Images/assets/products/full/cat1/MissingImage_BB.png";
    private int id;
    private String code;
    private String name;
    private String image;   // URL for image
    private String description;
    private String ingredients;
    private int price;  // price per 0.5 kg (pence)
    private int storeQuantity;  // weight in storage (kilograms)
    private int floorQuantity;  // weight on shop floor (kilograms)

    /**
     * Constructor for Product objects.
     */
    public Product(int id, String code, String name, String image, String description,
                   String ingredients, int price, int quantity)
    {
        this.id = id;
        this.code = code;
        this.name = name;
        if (image.isEmpty()) {
            this.image = DEFAULT_IMAGE;
        } else {
            setImage(image);
        }
        this.description = description;
        this.ingredients = ingredients;
        this.price = price;
        this.storeQuantity = quantity;
        this.floorQuantity = 0;
    }

    /**
     * The default constructor for this class
     */
    public Product()
    {
        image = DEFAULT_IMAGE;
        price = 0;
        storeQuantity = 0;
        floorQuantity = 0;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public int getPrice() {
        return price;
    }

    public int getStoreQuantity() {
        return storeQuantity;
    }

    public int getFloorQuantity() {
        return floorQuantity;
    }

    public int getTotalStockQuantity() {
        return storeQuantity + floorQuantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks that input string is in URL form and sets the image field accordingly.
     * @param image The URL string to be checked
     */
    public void setImage(String image) {
        try {
            new URL(image);
            this.image = image;
        }
        catch (MalformedURLException e) {
            this.image = DEFAULT_IMAGE;
            System.err.println("image string not a valid URL: " + e.toString());
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Updates the quantity of this product in storage.
     * @param weight The amount of the product in kilograms
     */
    public void updateStoreQuantity(int weight) {
        this.storeQuantity = storeQuantity + weight;
    }

    /**
     * Updates the quantity of this product on shop floor.
     * @param weight The amount of the product in kilograms
     */
    public void updateFloorQuantity(int weight) {
        this.floorQuantity = floorQuantity + weight;
    }

    /**
     * Sets this this object's fields according to input.
     *
     * @param input The text to be assigned
     * @param type The field type
     */
    public void setFieldFromInput(String input, AddProductEnum type)
    {
        switch (type) {
            case ID:
                if (ProductDataProcessor.isPositiveInteger(input)) {
                    id = Integer.parseInt(input);
                } else {
                    id = 0;
                }
                break;
            case CODE:
                code = input;
                break;
            case NAME:
                name = input;
                break;
            case IMAGE:
                if (!input.isEmpty()) {
                    setImage(input);
                } else {
                    image = DEFAULT_IMAGE;
                }
                break;
            case DESCRIPTION:
                description = input;
                break;
            case INGREDIENTS:
                ingredients = input;
                break;
            case PRICE:
                if (ProductDataProcessor.isPositiveInteger(input)) {
                    price = Integer.parseInt(input);
                } else {
                    price = 0;
                }
                break;
            case QUANTITY:
                if (ProductDataProcessor.isPositiveInteger(input)) {
                    storeQuantity = Integer.parseInt(input);
                } else {
                    storeQuantity = 0;
                }
                break;
        }
    }
}
