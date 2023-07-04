package com.redbadger.catfacts.shared_types;


public final class CatImage {
    public final String href;

    public CatImage(String href) {
        java.util.Objects.requireNonNull(href, "href must not be null");
        this.href = href;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_str(href);
        serializer.decrease_container_depth();
    }

    public byte[] bincodeSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bincode.BincodeSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static CatImage deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.href = deserializer.deserialize_str();
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static CatImage bincodeDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bincode.BincodeDeserializer(input);
        CatImage value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CatImage other = (CatImage) obj;
        if (!java.util.Objects.equals(this.href, other.href)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.href != null ? this.href.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public String href;

        public CatImage build() {
            return new CatImage(
                href
            );
        }
    }
}
