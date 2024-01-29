# Marvel App using Jetpack Compose

## Overview

This is a sample Marvel app built with Jetpack Compose for Android. The app allows users to explore and discover information about Marvel characters and comics.

## Features

- View a list of Marvel characters.
- Discover Marvel comics and their details.
- Marvel-themed user interface using Jetpack Compose.

## Screenshots

| Character List | Comic list | Comic Details |
|---------|---------|---------|
| ![Character_List](https://github.com/camedi445/marvel-comics/assets/23129361/be974e35-1b35-43f5-b8bb-7f5aaa046af1) | ![Comic_list](https://github.com/camedi445/marvel-comics/assets/23129361/ab06d033-7d96-47a5-9956-cb9a870d9a78) | ![comic_details](https://github.com/camedi445/marvel-comics/assets/23129361/008e44ff-15f7-4340-92d3-645bd146f0ec) |

# MVVM Architecture Overview

The MVVM (Model-View-ViewModel) architecture is a design pattern that separates concerns within an Android application, promoting modularization and maintainability.

## Components

### View:
- Represents the UI layer.
- Observe ViewModel for UI-related data.
- Utilizes Jetpack Compose or XML-based layouts.

### ViewModel:
- Acts as a mediator between the View and the Model.
- Manages UI-related data and business logic.
- Exposes observable LiveData or State for the View to observe.

### Model:
- Comprises the business logic and data layer.
- Consists of a domain layer for core business logic.
- May include repositories for data access, network, and local storage.

### Domain Layer:
- Contains the core business logic and use cases.
- Represents the business rules and operations.
- Decouples the business logic from the data layer.

## Flow of Data

1. View initiates UI action.
2. ViewModel processes the action.
   - Requests data from the domain layer.
   - Performs business logic.
   - Updates UI-related State.
3. View observes ViewModel changes.
   - Reacts to changes in UI-related data.
   - Displays updated information to the user.

## Image for Representation

![mad-arch-overview-ui](https://github.com/camedi445/marvel-comics/assets/23129361/182d1606-6ec9-4b58-b5a3-6972faf100a0)
