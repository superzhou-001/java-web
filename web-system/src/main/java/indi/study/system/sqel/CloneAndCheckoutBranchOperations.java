package indi.study.system.sqel;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 源代码信息
// 代码拉取，删除公司信息，给到客户方
public class CloneAndCheckoutBranchOperations {
    public static void main(String[] args) {
        String targetDirectory = "/Users/zhouming/Documents/sunyur/initProject";
        String[] gitRepositories = {
                "git@gitlab.sunyur.com:sunyur_basic_services/user_auth_web.git","git@gitlab.sunyur.com:sunyur_basic_services/user_center.git","git@gitlab.sunyur.com:sunyur_explore_code/wormhole_center.git","git@gitlab.sunyur.com:sunyur_support_code/refuel_center.git","git@gitlab.sunyur.com:sunyur_support_code/refuel_web.git","git@gitlab.sunyur.com:sunyur_explore_code/purchaser_center.git","git@gitlab.sunyur.com:sunyur_order_code/order_center.git","git@gitlab.sunyur.com:sunyur_basic_services/file_web.git","git@gitlab.sunyur.com:sunyur_product_code/office_web.git","git@gitlab.sunyur.com:sunyur_product_code/contract_center.git","git@gitlab.sunyur.com:sunyur_product_code/contract_web.git","git@gitlab.sunyur.com:sunyur_order_code/trade_purchaser_web.git","git@gitlab.sunyur.com:sunyur_explore_code/matrix_center.git","git@gitlab.sunyur.com:sunyur_source_code/activiti_center.git","git@gitlab.sunyur.com:sunyur_source_code/activiti_web.git","git@gitlab.sunyur.com:sunyur_source_code/source_center.git","git@gitlab.sunyur.com:sunyur_source_code/source_web.git","git@gitlab.sunyur.com:sunyur_source_code/supplier_center.git","git@gitlab.sunyur.com:sunyur_source_code/supplier_manage_web.git","git@gitlab.sunyur.com:sunyur_product_code/business_sharing_center.git","git@gitlab.sunyur.com:sunyur_basic_services/chat_center.git","git@gitlab.sunyur.com:sunyur_basic_services/chat_server.git","git@gitlab.sunyur.com:sunyur_basic_services/code_center.git","git@gitlab.sunyur.com:sunyur_product_code/ele_product_center.git","git@gitlab.sunyur.com:sunyur_product_code/product_center.git","git@gitlab.sunyur.com:sunyur_product_code/mall_common_center.git","git@gitlab.sunyur.com:sunyur_product_code/mall_purchaser_web.git","git@gitlab.sunyur.com:sunyur_server_code/mall_web.git","git@gitlab.sunyur.com:sunyur_basic_services/message_center.git","git@gitlab.sunyur.com:sunyur_apaas_services/apaas_biz_web.git","git@gitlab.sunyur.com:sunyur_apaas_services/apaas_center.git","git@gitlab.sunyur.com:sunyur_apaas_services/apaas_datafile_center.git","git@gitlab.sunyur.com:sunyur_apaas_services/apaas_design_web.git","git@gitlab.sunyur.com:sunyur_apaas_services/apaas_ops_center.git","git@gitlab.sunyur.com:sunyur_apaas_services/apaas_schema_center.git","git@gitlab.sunyur.com:sunyur_server_code/portal_web.git","git@gitlab.sunyur.com:sunyur_bigdata/bigdata_web.git","git@gitlab.sunyur.com:sunyur_bigdata/data_transfer_center.git","git@gitlab.sunyur.com:sunyur_bigdata/dpaas_manager_web.git","git@gitlab.sunyur.com:sunyur_server_code/purchaser_web.git","git@gitlab.sunyur.com:sunyur_server_code/supplier_web.git","git@gitlab.sunyur.com:sunyur_basic_services/sequence_factory.git","git@gitlab.sunyur.com:sunyur_basic_services/hermes_nex.git","git@gitlab.sunyur.com:sunyur_basic_services/log_service.git","git@gitlab.sunyur.com:sunyur_sop_code/app_component_server.git","git@gitlab.sunyur.com:sunyur_server_code/api_web.git","git@gitlab.sunyur.com:sunyur_basic_services/sy_i18n.git","git@gitlab.sunyur.com:sunyur_basic_services/sy_eai_center.git","git@gitlab.sunyur.com:sunyur_basic_services/sy_eai_web.git","git@gitlab.sunyur.com:sunyur_tailor_code/sy_operation_center.git","git@gitlab.sunyur.com:sunyur_basic_services/sy_tenant_center.git","git@gitlab.sunyur.com:sunyur_server_code/sop_csb_route.git","git@gitlab.sunyur.com:sunyur_basic_services/sy_tenant_manager_web.git","git@gitlab.sunyur.com:sunyur_apaas_services/low_code_center.git","git@gitlab.sunyur.com:sunyur_tailor_code/tailor_channel.git","git@gitlab.sunyur.com:sunyur_bigdata/sy_bigdata_center.git","git@gitlab.sunyur.com:sunyur_product_code/mall_common_share.git","git@gitlab.sunyur.com:sunyur_explore_code/matrix_share.git","git@gitlab.sunyur.com:sunyur_basic_services/user_share.git","git@gitlab.sunyur.com:sunyur_order_code/order_share.git","git@gitlab.sunyur.com:sunyur_source_code/source_share.git","git@gitlab.sunyur.com:sunyur_basic_services/message_share.git","git@gitlab.sunyur.com:sunyur_explore_code/purchaser_share.git","git@gitlab.sunyur.com:sunyur_basic_services/code_share.git","git@gitlab.sunyur.com:sunyur_basic_services/file_share.git","git@gitlab.sunyur.com:sunyur_source_code/activiti_share.git","git@gitlab.sunyur.com:sunyur_product_code/ele_product_share.git","git@gitlab.sunyur.com:sunyur_basic_services/sy_tenant_share.git","git@gitlab.sunyur.com:sunyur_server_code/sy_common_share.git","git@gitlab.sunyur.com:sunyur_basic_services/sequence_share.git","git@gitlab.sunyur.com:sunyur_product_code/product_shared.git","git@gitlab.sunyur.com:sunyur_basic_services/log_share.git","git@gitlab.sunyur.com:sunyur_basic_services/log_client.git","git@gitlab.sunyur.com:sunyur_bigdata/sy_bigdata_share.git","git@gitlab.sunyur.com:sunyur_source_code/supplier_share.git","git@gitlab.sunyur.com:sunyur_server_code/api_share.git","git@gitlab.sunyur.com:sunyur_server_code/common_share.git","git@gitlab.sunyur.com:sunyur_explore_code/wormhole_share.git","git@gitlab.sunyur.com:sunyur_product_code/contract_share.git","git@gitlab.sunyur.com:sunyur_basic_services/chat_share.git","git@gitlab.sunyur.com:sunyur_bigdata/data_transfer_share.git","git@gitlab.sunyur.com:sunyur_basic_services/user_auth_share.git","git@gitlab.sunyur.com:sunyur_basic_services/auth_common_share.git","git@gitlab.sunyur.com:sunyur_source_code/supplier_power_share.git","git@gitlab.sunyur.com:sunyur_apaas_services/apaas_share.git","git@gitlab.sunyur.com:sunyur_apaas_services/value_share.git","git@gitlab.sunyur.com:sunyur_order_code/trade_parent.git","git@gitlab.sunyur.com:sunyur_apaas_services/apaas_migrate_share.git","git@gitlab.sunyur.com:sunyur_basic_services/security_share.git","git@gitlab.sunyur.com:sunyur_fe_code/fe-coms.git","git@gitlab.sunyur.com:sunyur_fe_code/fe-m-coms.git","git@gitlab.sunyur.com:sunyur_basic_services/sy_tenant_manager_web.git","git@gitlab.sunyur.com:sunyur_order_code/trade_purchaser_web_common.git"
        };
        String branchName = "private_master_202403_extend";

        File targetDir = new File(targetDirectory);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(() -> {
            for (String repo : gitRepositories) {
                cloneAndCheckoutBranch(repo, targetDir, branchName);
            }
        });
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        //之后可以搭配 com.example.file.DirectoryCleaner   清理掉git文件

    }

    private static void cloneAndCheckoutBranch(String repo, File targetDir, String branchName) {
        String repoName = repo.substring(repo.lastIndexOf("/") + 1, repo.lastIndexOf("."));
        File repoDir = new File(targetDir, repoName);
        try {
            System.out.println("Cloning repo: " + repo);
            runCommand(targetDir, "git", "clone", repo);
            System.out.println("Checking out branch: " + branchName + " for repo: " + repo);
            runCommand(repoDir, "git", "checkout", branchName);

            // 处理仓库目录中的文件
            //processFiles(repoDir);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void processFiles(File directory) {
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.isDirectory()) {
                    processFiles(file);
                } else if (file.getName().equals("pom.xml")) {
                    processPomFile(file);
                } else if (file.getName().endsWith(".properties")) {
                    deleteFile(file);
                }
            }
        }
    }

    private static void processPomFile(File pomFile) {
        try {
            Path path = Paths.get(pomFile.getAbsolutePath());
            List<String> lines = Files.readAllLines(path);
            StringBuilder content = new StringBuilder();
            boolean inDistributionManagement = false;

            for (String line : lines) {
                if (line.contains("<distributionManagement>")) {
                    inDistributionManagement = true;
                }
                if (!inDistributionManagement) {
                    content.append(line).append(System.lineSeparator());
                }
                if (inDistributionManagement && line.contains("</distributionManagement>")) {
                    inDistributionManagement = false;
                }
            }

            if (content.length() != lines.size()) {
                Files.write(path, content.toString().getBytes(), StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("Removed distributionManagement from: " + pomFile.getAbsolutePath());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFile(File file) {
        if (file.delete()) {
            System.out.println("Deleted properties file: " + file.getAbsolutePath());
        } else {
            System.out.println("Failed to delete file: " + file.getAbsolutePath());
        }
    }

    private static void runCommand(File directory, String... command) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(directory);
        Process process = builder.start();
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new IOException("Command failed with exit code " + exitCode);
        }
    }
}
