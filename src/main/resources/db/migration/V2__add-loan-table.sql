# drop table if exists loan;
#
# create table loan (
#     id VARCHAR(36) NOT NULL PRIMARY KEY,
#     return_date DATETIME(6) DEFAULT NULL,
#     user_id VARCHAR(36) NOT NULL,
#     book_id VARCHAR(36) NOT NULL,
#     CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (id),
#     CONSTRAINT FOREIGN KEY (book_id) REFERENCES book (id)
# ) engine=InnoDB;