INSERT INTO public.users (id, login, password, phone_number, email, passport_series_and_number, created, changed, is_deleted) VALUES (67, 'user_1', '1_e1234e_1', '+375112341', '111wer.1@gmail.com', 'KH1_e1234e4671', '2023-04-16 16:17:58.405804', '2023-04-16 16:17:58.405804', false);
INSERT INTO public.users (id, login, password, phone_number, email, passport_series_and_number, created, changed, is_deleted) VALUES (70, 'user_21', '11_e1234e_13', '+37549123489', '111wer.102@gmail.com', 'KH35_e1234e4678', '2023-04-16 16:33:06.810363', '2023-04-16 16:33:06.810363', false);
INSERT INTO public.users (id, login, password, phone_number, email, passport_series_and_number, created, changed, is_deleted) VALUES (71, 'user_12', '42_e1234e_84', '+37572123422', '111wer.46@gmail.com', 'KH56_e1234e46773', '2023-04-16 16:33:13.633717', '2023-04-16 16:33:13.633717', false);
INSERT INTO public.users (id, login, password, phone_number, email, passport_series_and_number, created, changed, is_deleted) VALUES (72, 'user_20', '65_e1234e_63', '+37537123447', '111wer.37@gmail.com', 'KH73_e1234e46798', '2023-04-16 16:33:38.792854', '2023-04-16 16:33:38.792854', false);
INSERT INTO public.users (id, login, password, phone_number, email, passport_series_and_number, created, changed, is_deleted) VALUES (73, 'user_105', '51_e1234e_42', '+37548123476', '111wer.99@gmail.com', 'KH98_e1234e46738', '2023-04-16 16:33:39.724757', '2023-04-16 16:33:39.724757', false);
INSERT INTO public.users (id, login, password, phone_number, email, passport_series_and_number, created, changed, is_deleted) VALUES (74, 'user_14', '9_e1234e_21', '+37563123495', '111wer.26@gmail.com', 'KH38_e1234e467102', '2023-04-16 16:33:40.634370', '2023-04-16 16:33:40.634370', false);
INSERT INTO public.users (id, login, password, phone_number, email, passport_series_and_number, created, changed, is_deleted) VALUES (65, 'user_65', '68_e1234e_65', '+37568123465', 'user_65.65@gmail.com', 'KH68_e1234e46765', '2023-04-16 15:49:15.043524', '2023-04-16 15:49:15.053379', false);
INSERT INTO public.users (id, login, password, phone_number, email, passport_series_and_number, created, changed, is_deleted) VALUES (66, 'user_66', '69_e1234e_66', '+37569123466', 'user_66.66@gmail.com', 'KH69_e1234e46766', '2023-04-16 15:49:15.043524', '2023-04-16 15:49:15.053379', false);
INSERT INTO public.users (id, login, password, phone_number, email, passport_series_and_number, created, changed, is_deleted) VALUES (4, 'user_003', 'z4z4z4z4z', '+90874586', 'kjvu@er56', 'dg57reu6ie9', '2023-04-22 22:00:01.761366', '2023-04-22 22:00:01.761366', false);
INSERT INTO public.users (id, login, password, phone_number, email, passport_series_and_number, created, changed, is_deleted) VALUES (5, 'user', '10000008', '+190874586', '1kjvu@er56', '1dg57reu6ie9', '2023-04-22 23:07:52.285192', '2023-04-22 23:07:52.285192', false);

INSERT INTO public.roles (id, role_name, user_id, created, changed) VALUES (2, 'ROLE_USER', 65, '2023-04-16 20:42:30.000000', '2023-04-16 20:42:31.000000');
INSERT INTO public.roles (id, role_name, user_id, created, changed) VALUES (1, 'ROLE_ADMIN', 65, '2023-04-16 20:42:13.000000', '2023-04-16 20:42:14.000000');
INSERT INTO public.roles (id, role_name, user_id, created, changed) VALUES (3, 'ROLE_USER', 67, '2023-04-16 20:43:01.000000', '2023-04-16 20:43:02.000000');
INSERT INTO public.roles (id, role_name, user_id, created, changed) VALUES (4, 'ROLE_ADMIN', 4, '2023-04-23 23:51:38.000000', '2023-04-23 23:51:44.000000');
INSERT INTO public.roles (id, role_name, user_id, created, changed) VALUES (5, 'ROLE_ADMIN', 5, '2023-04-23 23:51:39.000000', '2023-04-23 23:51:45.000000');
INSERT INTO public.roles (id, role_name, user_id, created, changed) VALUES (6, 'ROLE_USER', 70, '2023-04-23 23:51:40.000000', '2023-04-23 23:51:46.000000');
INSERT INTO public.roles (id, role_name, user_id, created, changed) VALUES (7, 'ROLE_USER', 71, '2023-04-23 23:51:41.000000', '2023-04-23 23:51:47.000000');
INSERT INTO public.roles (id, role_name, user_id, created, changed) VALUES (8, 'ROLE_USER', 72, '2023-04-23 23:51:43.000000', '2023-04-23 23:51:48.000000');
INSERT INTO public.roles (id, role_name, user_id, created, changed) VALUES (9, 'ROLE_USER', 73, '2023-04-23 23:51:32.000000', '2023-04-23 23:51:33.000000');

INSERT INTO public.regions (id, number, address_range, created, is_deleted) VALUES (2, 1, 'qwe', '2023-04-20 00:17:47.000000', false);
INSERT INTO public.regions (id, number, address_range, created, is_deleted) VALUES (1, 2, 'wtej', '2023-04-23 19:33:17.000000', false);

INSERT INTO public.patients (card_number, name, surname, gender, birthday_data, address, created, changed, is_deleted, id_user, region_number) VALUES (2, 'qaz', 'ghty', 'NOT_SELECTED', '2023-04-23 19:25:35.000000', 'hdyrltiryhfg', '2023-04-23 19:25:40.000000', '2023-04-23 19:25:41.000000', false, 5, 1);
INSERT INTO public.patients (card_number, name, surname, gender, birthday_data, address, created, changed, is_deleted, id_user, region_number) VALUES (3, 'feqgt3', 'aswrty', 'NOT_SELECTED', '2023-04-23 19:32:48.000000', 'dqerwhtrbef', '2023-04-23 19:32:51.000000', '2023-04-23 19:32:52.000000', false, 67, 2);
