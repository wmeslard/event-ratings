# Java - CDP Recruitment

### 1. Adding review does not work

After launching the `spring-boot:run` command, I attempted to add a review on the front-end side with the network dev tool opened.

Nothing seems to be wrong on that side:
A PUT request with a 200 OK response, and the payload seems correct with my comment and the number of stars present.

Next, I followed the data path: the next step is the controller.
As we only have one controller in this project, it’s easy to find it, but normally I would search for the `api/events/` request.
Here we acknowledge the first problem: in the `EventController.java` class, the `updateEvent` method lacks an implementation; the body is empty.
My next move is to glance at the `EventService` to see if the `update` method is defined there.

In `EventService`, I did not find an `update` method, so I’ll have to implement it as well.
As I don’t want to update the whole entity, I’ll change the API mapping method to `@PatchMapping`, as it is more recommended for small changes in the entity (not overriding the whole object).

I’ll also return the `Event` object in the method, as it is more convenient and could be useful for my consumers. The `Event` object is not so big and won’t cause latency issues.

In the `update` method in the event service, I only update the number of stars and the comment, as the other parameters are not supposed to change (or at least it’s not the goal of this specific API).

I created a specific `EventNotFoundException`, thrown if an event is not found in the database while attempting to update.

### 2. Fixing the delete method

I saw earlier that the event entity has cascade relationships with `Band`, which is why the delete method is not working: indeed, the `event_bands` table has foreign key constraints referencing the `event` and `band` tables.
We need to propagate the delete to that table, which is done by adding the `@Transactional` annotation.

### 3. New feature

The feature specifies that we should filter events by band member name.
After filtering, the event should remain the same (with all bands and members, including those that don’t match the filter).
The problem is that the given example doesn’t fit that description: the example shows a filtered event that only contains the matching member.
I will assume that we want to remove non-matching members and bands, as shown in the example.

I use Bruno to check if my API is correctly responding.

I use nested loops to filter each member and each band.

I would like to not modify the entity itself, so I’ll use DTOs. This will allow me to send only the data that I want to. (Currently, we send back the whole entity in the DTO but it could change in the future.)
I would like to use Lombok (MapStruct, Setter, Getter, Constructor, equals and hashCode) but as it is specified to not use external libraries, I will build all that myself.
In addition, if I were able to use external libraries, I would also add documentation to the API with Swagger.
I also change the API responses with ResponseEntity, it will allow me to test the status code and the body of the response.

### 4. Adding tests

Now that my application is correctly fixed and the new feature added, I’ll build some tests for `EventController`, `EventService`, and also for each mapper.
I’ll use AssertJ and Mockito, and I’ll add some JSON as test resources, as it is more readable and allows tests to be shorter, with no need to create DTOs and entities in the test classes.
These JSON files are the same for seed and expected, because currently we have DTOs equal to entities. That’s why in the mapper tests, we use the same expected/seed JSON both for entity and DTO.

### 5. Git

I should have created the Git repository in the first place and also updated the `.gitignore` accordingly.
I created the Git repo after the second step, which is why there are multiple commits.