package com.example.book.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Library {
	
	private Integer bookId;
	@NotBlank(message="Book nam eis required")
	private String bookName;
	@NotBlank(message="Author name is required")
	private String author;
	@NotNull(message="price is required")
	@Positive(message="Price must be positive")
	private Double price;
	
	private Boolean availability;
	
	@Min(value=1,message="copies must atleast 1")
	private Integer totalCopies;

}
