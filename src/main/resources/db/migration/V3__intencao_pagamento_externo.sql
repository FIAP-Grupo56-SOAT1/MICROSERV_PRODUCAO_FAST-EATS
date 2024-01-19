ALTER TABLE `pagamentos` ADD COLUMN `idPagamentoExterno` BIGINT NULL DEFAULT NULL AFTER `dataHoraFinalizado`;
ALTER TABLE `pagamentos` ADD COLUMN `urlPagamento`       VARCHAR(1000) NULL DEFAULT NULL AFTER `idPagamentoExterno`;
ALTER TABLE `pagamentos` ADD COLUMN `qrcode`             VARCHAR(2000) NULL DEFAULT NULL AFTER `urlPagamento`;
INSERT INTO `statuspagamento` (`id`, `nome`, `ativo`)    VALUES (4, 'EM_PROCESSAMENTO', 1);