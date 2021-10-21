# Mocking Ecommerce Kata

## Goal

Implement ecommerce using TDD and without waiting for the necessary services to be available.

## Given

1. We have a Basket that contains a list of Items. When the Basket is initialized, its list of Items is empty.
2. If a customer has already used our ecommerce and has added elements in the Basket, when the Basket is initialized, their elements must be recovered, and we must validate that they are in the Basket.
3. Our ecommerce also has a Payment Service receives a logger to be able to log the traces and can create a Paymen Request with the amount of the Basket and a credit card. It is not necessary to check the logger, only that the Payment Request has been created correctly.
4. Now the Payment Request has to contain the fee of the credit card operator, the rate of this fee is defined by the credit card operator.
5. If the amount of the basket is greater than 1000, an email must be sent to the Administrator.

## How to do it?

### Test the code using test doubles created by you.

1. Create the Basket and Item objects and check that the new Basket has no items.
2. To save the items in the basket, you can create a Fake Basket Repository, which will be an in-memory repository.
3. Since the logger doesn't need to be validated, we can create a Dummy Logger that doesn't need any logic.
4. To be able to program a behavior of the Operator Rate, we can create a stub.
5. As the sending of the email is not real, and we must validate if the send method has been called, we need to implement a Mock.

### Test the code using test doubles created with a library.

6. Replace Test Double with Mockito
7. Use Mockito annotations

## Learnings

- How to build a Fake, Dummy, Stub and a Mock manually.

- How to use Mockito to generate the test doubles.
