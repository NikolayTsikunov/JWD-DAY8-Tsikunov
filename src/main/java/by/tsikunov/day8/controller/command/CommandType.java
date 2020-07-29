package by.tsikunov.day8.controller.command;

import by.tsikunov.day8.controller.command.impl.*;

public enum CommandType {
    ADD_BOOK(new AddBookCommand()),
    REMOVE_BOOK(new RemoveBookCommand()),
    FIND_ALL(new FindAllCommand()),
    FIND_ID(new FindByIdCommand()),
    FIND_TITLE(new FindByTitleCommand()),
    FIND_AUTHOR(new FindByAuthorCommand()),
    FIND_YEAR_PUBLICATION(new FindByPublicationYearCommand()),
    FIND_PAGE(new FindByPagesCommand()),
    SORT_BY_OPTION(new SortByOptionCommand()),
    EMPTY_COMMAND(new EmptyCommand());

    private final ExecutedCommand command;

    CommandType(ExecutedCommand command) {
        this.command = command;
    }

    public ExecutedCommand getCommand(){
        return command;
    }
}
