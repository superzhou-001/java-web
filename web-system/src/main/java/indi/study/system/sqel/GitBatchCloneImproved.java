package indi.study.system.sqel;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GitBatchCloneImproved {
    private static final Logger LOGGER = LoggerFactory.getLogger(GitBatchCloneImproved.class);

    /**
     * 批量克隆 Git 仓库
     *
     * @param repoUrls       仓库 URL 列表
     * @param targetDir      克隆到的目标目录
     * @param username       Git 用户名（如果需要身份验证）
     * @param password       Git 密码或 Token（如果需要身份验证）
     * @param maxConcurrency 最大并发数
     */
    public static void cloneRepositories(List<String> repoUrls, String targetDir, String username, String password, String branchName, int maxConcurrency) {
        ExecutorService executorService = Executors.newFixedThreadPool(maxConcurrency);

        for (String repoUrl : repoUrls) {
            executorService.submit(() -> {
                try {
                    String repoName = repoUrl.substring(repoUrl.lastIndexOf("/") + 1).replace(".git", "");
                    File destination = new File(targetDir, repoName);
                    LOGGER.info("开始克隆仓库: {} 到目录: {}", repoUrl, destination.getAbsolutePath());

                    Git.cloneRepository()
                            .setURI(repoUrl)
                            .setDirectory(destination)
                            .setCredentialsProvider(
                                    username != null && password != null
                                            ? new UsernamePasswordCredentialsProvider(username, password)
                                            : null
                            )
                            .setBranch(branchName)
                            .call();

                    LOGGER.info("成功克隆仓库: {}", repoUrl);
                } catch (GitAPIException e) {
                    LOGGER.error("克隆失败: {}", repoUrl, e);
                }
            });
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error("线程等待中断", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        // 示例：仓库 URL 列表
        List<String> repoUrls = Arrays.asList(
                "git@gitlab.sunyur.com:sunyur_product_code/product_center.git",
                "git@gitlab.sunyur.com:sunyur_server_code/mall_web.git"
        );

        // 目标目录
        String targetDir = "/Users/zhouming/Documents/sunyur/initProject";

        // 用户凭据（可选）
        String username = "zhouming.zm"; // 替换为你的用户名
        String password = "Zm11.09ming"; // 替换为你的密码或 token
        String branchName = "private_master_202403_extend";

        // 批量克隆（最大并发数为 4）
        cloneRepositories(repoUrls, targetDir, username, password, branchName, 1);
    }
}