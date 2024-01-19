DROP TABLE IF EXISTS `categorias`;
CREATE TABLE IF NOT EXISTS `categorias`
(
    `id`        bigint     NOT NULL AUTO_INCREMENT,
    `nome`      varchar(200) DEFAULT NULL,
    `descricao` varchar(400) DEFAULT NULL,
    `ativo`     tinyint(1) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `produtos`;
CREATE TABLE IF NOT EXISTS `produtos`
(
    `id`             bigint       NOT NULL AUTO_INCREMENT,
    `nome`           varchar(200) NOT NULL,
    `descricao`      varchar(400) DEFAULT NULL,
    `valor`          double       NOT NULL,
    `ativo`          tinyint(1)   NOT NULL,
    `categoriaId_fk` bigint       NOT NULL,
    `imagemBase64`   longtext,
    `imagemUrl`      varchar(400) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `tipoProdutoId_fk` (`categoriaId_fk`),
    CONSTRAINT `produtos_ibfk_1` FOREIGN KEY (`categoriaId_fk`) REFERENCES `categorias` (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes`
(
    `cpf`          varchar(11) NOT NULL,
    `primeironome` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `ultimonome`   varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `email`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `ativo`        tinyint(1)                                                    DEFAULT NULL,
    PRIMARY KEY (`cpf`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `formapagamentos`;
CREATE TABLE IF NOT EXISTS `formapagamentos`
(
    `id`    bigint     NOT NULL AUTO_INCREMENT,
    `nome`  varchar(200) DEFAULT NULL,
    `externo` tinyint(1) NOT NULL DEFAULT 0,
    `ativo` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `statuspagamento`;
CREATE TABLE IF NOT EXISTS `statuspagamento`
(
    `id`    bigint     NOT NULL AUTO_INCREMENT,
    `nome`  varchar(200) DEFAULT NULL,
    `ativo` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `statuspedidos`;
CREATE TABLE IF NOT EXISTS `statuspedidos`
(
    `id`    bigint     NOT NULL AUTO_INCREMENT,
    `nome`  varchar(200) DEFAULT NULL,
    `ativo` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `pedidos`;
CREATE TABLE IF NOT EXISTS `pedidos`
(
    `id`                  bigint NOT NULL AUTO_INCREMENT,
    `clienteId_fk`        varchar(11) DEFAULT NULL,
    `ativo`               tinyint(1)  DEFAULT NULL,
    `statusPedidoId_fk`   bigint      DEFAULT NULL,
    `dataHoraCriado`      datetime    DEFAULT NULL,
    `dataHoraRecebimento` datetime    DEFAULT NULL,
    `dataHoraFinalizado`  datetime    DEFAULT NULL,
    `valor`               double      DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `clienteId_fk` (`clienteId_fk`),
    KEY `statusPedidoId_fk` (`statusPedidoId_fk`),
    CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`clienteId_fk`) REFERENCES `clientes` (`cpf`),
    CONSTRAINT `pedidos_ibfk_2` FOREIGN KEY (`statusPedidoId_fk`) REFERENCES `statuspedidos` (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `produtosdeumpedido`;
CREATE TABLE IF NOT EXISTS `produtosdeumpedido`
(
    `pedidoId_fk`  bigint NOT NULL,
    `produtoId_fk` bigint NOT NULL,
    `quantidade`   int    NOT NULL,
    `valor`        double NOT NULL,
    PRIMARY KEY (`pedidoId_fk`, `produtoId_fk`),
    KEY `pedidoId_fk` (`pedidoId_fk`),
    KEY `produtoId_fk` (`produtoId_fk`),
    CONSTRAINT `produtosdeumpedido_ibfk_1` FOREIGN KEY (`pedidoId_fk`) REFERENCES `pedidos` (`id`),
    CONSTRAINT `produtosdeumpedido_ibfk_2` FOREIGN KEY (`produtoId_fk`) REFERENCES `produtos` (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `pagamentos`;
CREATE TABLE IF NOT EXISTS `pagamentos`
(
    `id`                    bigint NOT NULL AUTO_INCREMENT,
    `formaPagamentoId_fk`   bigint NOT NULL,
    `statusPagamentoId_fk`  bigint NOT NULL,
    `pedidoId_fk`           bigint NOT NULL,
    `dataHoraCriado`        datetime DEFAULT NULL,
    `dataHoraProcessamento` datetime DEFAULT NULL,
    `dataHoraFinalizado`    datetime DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `pedidoId_fk` (`pedidoId_fk`),
    KEY `formaPagamentoId_fk` (`formaPagamentoId_fk`),
    KEY `statusPagamentoId_fk` (`statusPagamentoId_fk`),
    CONSTRAINT `pagamentos_ibfk_1` FOREIGN KEY (`pedidoId_fk`) REFERENCES `pedidos` (`id`),
    CONSTRAINT `pagamentos_ibfk_2` FOREIGN KEY (`formaPagamentoId_fk`) REFERENCES `formapagamentos` (`id`),
    CONSTRAINT `pagamentos_ibfk_3` FOREIGN KEY (`statusPagamentoId_fk`) REFERENCES `statuspagamento` (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;