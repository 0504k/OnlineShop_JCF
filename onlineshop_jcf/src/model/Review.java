package model;
import java.time.LocalDate; import java.util.Objects;
public class Review {
    private int id;
    private User user;
    private Product product;
    private int rating;
    private String comment;
    private LocalDate date;

    public Review(int id, User user, Product product,
                  int rating, String comment, LocalDate date) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }
        if (user == null || product == null) {
            throw new IllegalArgumentException("User and product are required.");
        }
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        if (comment == null || comment.isBlank()) {
            throw new IllegalArgumentException("Comment cannot be empty.");
        }

        this.id = id;
        this.user = user;
        this.product = product;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Review)) return false;

        Review review = (Review) obj;
        return id == review.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Review ID: " + id
                + " | User: " + user.getName()
                + " | Product: " + product.getName()
                + " | Rating: " + rating + "/5"
                + " | Comment: " + comment
                + " | Date: " + date;
    }}