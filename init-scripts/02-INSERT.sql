USE spotitube;

-- -----------------------------------------------------
-- Table `spotitube`.`Users`
-- -----------------------------------------------------
INSERT INTO
  Users (`user`, `password`)
VALUES
  ('joelchrist', 'test1234'),
  ('johndoe', 'test1234');

-- -----------------------------------------------------
-- Table `spotitube`.`Tracks`
-- -----------------------------------------------------
INSERT INTO
  Tracks (`id`, `title`, `performer`, `duration`, `album`, `play_count`, `publication_date`, `description`, `offline_available`)
VALUES
  (1, 'Man\'s not hot', 'Big Shaq', 180, NULL, 4, NOW(), 'Best song ever', 0),
  (2, 'rockstar', 'Post Malone ft. 21 Savage', 228, 'rockstar', 6, NOW(), 'Single by former country singer Post Malone featuring rapper 21 Savage', 1),
  (3, 'Havana', 'Camila Cabello ft. Young Thug', 227, 'Havana', 28, NOW(), 'Single by singer Camila Cabello featuring rapper Young Thug', 0);

-- -----------------------------------------------------
-- Table `spotitube`.`Playlists`
-- -----------------------------------------------------
INSERT INTO
  Playlists (`id`, `name`, `owner`)
VALUES
  (1, 'Joel\'s playlist', 'joelchrist'),
  (2, 'Slow Jamz', 'johndoe'),
  (3, 'Rap / Hip-Hop', 'joelchrist');

-- -----------------------------------------------------
-- Table `spotitube`.`PlaylistTrack`
-- -----------------------------------------------------
INSERT INTO
  PlaylistTrack (`track_id`, `playlist_id`)
VALUES
  (1, 3),
  (2, 3),
  (3, 3),
  (2, 1),
  (2, 2),
  (3, 2);


