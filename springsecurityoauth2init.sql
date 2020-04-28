Drop table  if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(36) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information TEXT,
  autoapprove VARCHAR (255) default 'false'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('client_2', 'order', '{bcrypt}$2a$04$NqiScrd3C57bCFTOynqfEu.3MIpiI2wtL1OzxHCneMyWdWeIBNA9C', 'select', 'password', '', 'client', '3600', '3600', NULL, 'false');
INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('client_3', 'order', '{bcrypt}$2a$04$NqiScrd3C57bCFTOynqfEu.3MIpiI2wtL1OzxHCneMyWdWeIBNA9C', 'all', 'authorization_code', 'http://www.baidu.com', NULL, '3600', '3600', NULL, 'false');
