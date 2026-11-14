package shop.exception;

/** Thrown when the catalog file cannot be read at all (file missing, I/O error). */
public class CatalogLoadException extends ShopException {
    private static final long serialVersionUID = 1L;

    public CatalogLoadException(String message) {
        super(message);
    }

    public CatalogLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}