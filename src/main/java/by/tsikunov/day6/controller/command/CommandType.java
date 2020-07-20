package by.tsikunov.day6.controller.command;

import by.tsikunov.day6.controller.command.impl.*;

public enum CommandType {
    ADD_BOOK(new AddBookCommand()),
    REMOVE_BOOK(new RemoveBookCommand()),
    FIND_ALL(new FindAllCommand()),
    FIND_ID(new FindByIdCommand()),
    FIND_TITLE(new FindByTitleCommand()),
    FIND_AUTHOR(new FindByAuthorCommand()),
    FIND_YEAR_PUBLICATION(new FindByPublicationYearCommand()),
    FIND_LANGUAGE(new FindByPagesCommand()),
    SORT_ID(new SortByIdCommand()),
    SORT_TITLE(new SortByTitleCommand()),
    SORT_AUTHOR(new SortByAuthorCommand()),
    SORT_YEAR_PUBLICATION(new SortByPublicationYearCommand()),
    SORT_LANGUAGE(new SortByPagesCommand()),
    EMPTY_COMMAND(new EmptyCommand());

    private final ExecutedCommand command;

    CommandType(ExecutedCommand command) {
        this.command = command;
    }

    public ExecutedCommand getCommand(){
        return command;
    }
}
