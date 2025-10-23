package utilities;

/** Small helpers for converting loosely-typed JSON values in tests. */
public final class Conversions {

    /**
     * Returns the {@code int} value for a loosely-typed JSON field.
     *
     * <p>If {@code o} is a {@link Number}, its {@code intValue()} is returned. Otherwise,
     * {@code String.valueOf(o)} is parsed via {@link Integer#parseInt(String)}.</p>
     *
     * @param o a value that may be a {@code Number} or a {@code String}-like value
     * @return the parsed integer value
     * @throws NumberFormatException if {@code o} cannot be parsed as an integer
     */
    public static int asInt(Object o) {
        if (o instanceof Number) return ((Number) o).intValue();
        return Integer.parseInt(String.valueOf(o));
    }

    /**
     * Returns the string representation of a value, null-safe.
     *
     * <p>If {@code o} is {@code null}, returns {@code null}; otherwise returns
     * {@code String.valueOf(o)}.</p>
     *
     * @param o any value, possibly {@code null}
     * @return {@code null} if the input is {@code null}; otherwise the string representation
     */
    public static String asString(Object o) {
        return o == null ? null : String.valueOf(o);
    }
}
