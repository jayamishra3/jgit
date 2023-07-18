package org.example.jgit.jgitutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;

public class gitServices {
    //logger for logging
    private static final Logger logger = LoggerFactory.getLogger(gitServices.class);

    //location of git repository
    private final String localPath;

    //remote git repository
    private  final String remotePath;

    //parameterized constructor
    public gitServices(String localPath, String remotePath) {
        this.localPath = localPath;
        this.remotePath = remotePath;
    }

//create main method
    public static void main(String[] args) {
        String localPath = "D:\\petClinicApp\\tempJgit";
        String remotePath = "https://github.com/jayamishra3/testJgit.git";
        gitServices gitServices = new gitServices(localPath, remotePath);
        try {   
            //gitServices.cloneRepo();
            //gitServices.addCommitAllFile("First commit");
gitServices.pushFile();
            //gitServices.addFile();
            //gitServices.commitFile();
            //gitServices.pushFile();
            //gitServices.pullFile();
            //gitServices.createBranch("testBranch");
           // gitServices.checkStatus();
            
        } catch (Exception e) {
            logger.error("error occurred while cloning repo from {} to {}", remotePath, localPath, e);
        }
    }

    // method to check git status
    
    public void checkStatus() {
        logger.info("checking status of repo {}", localPath);
        try (Git git = Git.open(new File(localPath))) {
            logger.info("Added file: " , git.status().call().getAdded());
            logger.info("Changed file: " , git.status().call().getChanged());
            logger.info("Conflicting file: " , git.status().call().getConflicting());
            logger.info("Conflicting stage state: " , git.status().call().getConflictingStageState());
            logger.info("Ignored file: " , git.status().call().getIgnoredNotInIndex());
            logger.info("Missing file: " , git.status().call().getMissing());
            logger.info("Modified file: " , git.status().call().getModified());
            logger.info("Removed file: " , git.status().call().getRemoved());
            logger.info("Uncommitted changes: " , git.status().call().getUncommittedChanges());

            logger.info("Untracked file: " , git.status().call().getUntracked());

            logger.info("Untracked folder: " , git.status().call().getUntrackedFolders());
        } catch (IOException | GitAPIException e) {
            logger.error("error occurred while checking status of repo {}", localPath, e);
        }
    }

    //method to addcommitall file to git repository
    public void addCommitAllFile(String commitMsg) {
        logger.info("adding file to repo {}", localPath);
        try (Git git = Git.open(new File(localPath))) {
            git.add()
                    .addFilepattern(".")
                    .call();
            git.commit()
                    .setMessage(commitMsg)
                    .call();
        } catch (IOException | GitAPIException e) {
            logger.error("error occurred while adding file to repo {}", localPath, e);
        }
    }
    //method to clone git repository
    public void cloneRepo() {
        logger.info("cloning repo from {} to {}", remotePath, localPath);
        try {
            Git.cloneRepository()
                    .setURI(remotePath)
                    .setDirectory(new File(localPath))
                    .call();
        } catch (GitAPIException e) {
            logger.error("error occurred while cloning repo from {} to {}", remotePath, localPath, e);
        }
    }

    //method to add file to git repository
    public void addFile() {
        logger.info("adding file to repo {}", localPath);
        try (Git git = Git.open(new File(localPath))) {
            git.add()
                    .addFilepattern(".")
                    .call();
        } catch (IOException | GitAPIException e) {
            logger.error("error occurred while adding file to repo {}", localPath, e);
        }
    }

    //method to commit file to git repository
    public void commitFile() {
        logger.info("committing file to repo {}", localPath);
        try (Git git = Git.open(new File(localPath))) {
            git.commit()
                    .setMessage("First commit")
                    .call();
        } catch (IOException | GitAPIException e) {
            logger.error("error occurred while committing file to repo {}", localPath, e);
        }
    }

    //method to push file to git repository with username and password
    public void pushFile() {
        logger.info("pushing file to repo {}", localPath);
        try (Git git = Git.open(new File(localPath))) {
            git.push()
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(System.getenv("GITHUB_USER"), System.getenv("GITHUB_TOKEN")))
                    .call();
        } catch (IOException | GitAPIException e) {
            logger.error("error occurred while pushing file to repo {}", localPath, e);
        }
    }
    

    //method to pull file from git repository
    public void pullFile() {
        logger.info("pulling file from repo {}", localPath);
        try (Git git = Git.open(new File(localPath))) {
            git.pull()
                    .call();
        } catch (IOException | GitAPIException e) {
            logger.error("error occurred while pulling file from repo {}", localPath, e);
        }
    }

    //method to create branch in git repository
    public void createBranch(String branchName) {
        logger.info("creating branch {} in repo {}", branchName, localPath);
        try (Git git = Git.open(new File(localPath))) {
            git.branchCreate()
                    .setName(branchName)
                    .call();
        } catch (IOException | GitAPIException e) {
            logger.error("error occurred while creating branch {} in repo {}", branchName, localPath, e);
        }
    }
    
}

