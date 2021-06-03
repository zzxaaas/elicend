package cn.zzxcloud.elicend.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import cn.zzxcloud.elicend.common.vo.GitVO;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Git操作工具类
 */
public class JGitUtil {

    private GitVO gitVO;

    public enum SqlTypeEnum{
        SQL_CALC,EMAIL,MYSQL_TO_HIVE,HIVE_TO_MYSQL
    }

    public JGitUtil(GitVO gitVO) {
        this.gitVO = gitVO;
    }

    final static Logger LOG = LoggerFactory.getLogger(JGitUtil.class);

    //    public static void main(String[] args) {
//
//        writeFileToGit(SqlTypeEnum.EMAIL,"xxx","-- 测试hehe \n select * from dual;","test_测试_201806071322","test");
//
//    }
    /**
     * sql脚本文件同步到git仓库
     * @param qte SQl类型
     * @param loginName 系统登录名
     * @param fileName 文件名
     * @param fileContent  文件内容
     * @param comment  提交说明
     * @return
     */
    public boolean writeFileToGit(SqlTypeEnum qte,String loginName,String fileContent,String fileName,String comment){

        pull();
        String dest =gitVO.getLocalCodeCTSqlDir()+qte.name().toLowerCase();
        String path = gitVO.getLocalRepoPath()+"/"+dest;
        File f =new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        dest=dest+"/"+fileName;
        path=path+"/"+fileName;
        comment=loginName+" option of "+comment;
        return true == createFile(fileContent, path)==commitAndPush(dest,comment);
    }

    /**
     * 根据主干master新建分支并同步到远程仓库
     * @param branchName 分支名
     * @throws IOException
     * @throws GitAPIException
     */
    public String newBranch(String branchName) throws IOException{
//        String newBranchIndex = "refs/heads/"+branchName;
        String gitPathURI = "";
//        Git git = null;
//        try {
//
//            //检查新建的分支是否已经存在，如果存在则将已存在的分支强制删除并新建一个分支
//            List<Ref> refs = git.branchList().call();
//            for (Ref ref : refs) {
//                if (ref.getName().equals(newBranchIndex)) {
//                    System.out.println("Removing branch before");
//                    git.branchDelete().setBranchNames(branchName).setForce(true).call();
//                    break;
//                }
//            }
//            //新建分支
//            Ref ref = git.branchCreate().setName(branchName).call();
//            //推送到远程
//            git.push().add(ref).call();
//            gitPathURI = remoteRepoURI + " " + "feature/" + branchName;
//        } catch (GitAPIException e) {
//            e.printStackTrace();
//        }
        return gitPathURI;
    }
    /**
     * 添加文件
     * @param fileName
     * @return
     */
    public boolean addFile(String fileName) {

        boolean addFileFlag=true;
        try (
                Git git = Git.open( new File(gitVO.getLocalRepoGitConfig()) );
//            		Git git2 = new Git(new FileRepository(localRepoGitConfig));
        ) {
//            	  File myFile = new File(git.getRepository().getDirectory().getParent(),filePath);
//                if(!myFile.createNewFile()) {
//                    throw new IOException("Could not create file " + myFile);
//                }
            //add file to git
            String filePath=gitVO.getLocalCodeCTSqlDir()+fileName;
            git.add().addFilepattern(gitVO.getInitLocalCodeDir()).call();
            System.out.println("Added file " + filePath + " to repository at " + git.getRepository().getDirectory());
        }catch (Exception e) {
            e.printStackTrace();
            addFileFlag=false;
        }
        return addFileFlag;
    }
    /**
     * 提交代码到本地仓库
     * @param comment 提交git内容描述
     * @return
     */
    public boolean commitFile(String comment) {

        boolean commitFileFlag=true;
        try (Git git = Git.open( new File(gitVO.getLocalRepoGitConfig()) );) {
            //提交代码到本地仓库
            git.commit().setMessage(comment).call();
//            LOG.info("Committed to repository at " + git.getRepository().getDirectory());
        }catch (Exception e) {
            e.printStackTrace();
            commitFileFlag=false;
//            LOG.error("commitFile error! \n"+e.getMessage());
        }
        return commitFileFlag;
    }

    public boolean push() {

        boolean pushFlag=true;
        try (Git git = Git.open( new File(gitVO.getLocalRepoGitConfig()) );) {
            //提交代码到本地仓库
            git.push().call();
//            LOG.info("push " + git.getRepository()+File.separator+git.getRepository().getBranch());
        }catch (Exception e) {
            e.printStackTrace();
            pushFlag=false;
//            LOG.error("push error! \n"+e.getMessage());
        }
        return pushFlag;
    }
    /**
     * 提交并推送代码至远程服务器
     * @param filePath 提交文件路径(相对路径)
     * @param desc 提交描述
     * @return
     */
    public boolean commitAndPush(String filePath,String desc){

        boolean commitAndPushFlag=true;
        try (Git git = Git.open( new File(gitVO.getLocalRepoGitConfig()) );) {
//        	 //创建用户文件的过程
//             File myfile = new File(filePath);
//             myfile.createNewFile();
            UsernamePasswordCredentialsProvider provider =new UsernamePasswordCredentialsProvider(gitVO.getGitUsername(),gitVO.getGitPassword());
            git.add().addFilepattern(filePath).call();
            //提交
            git.commit().setMessage(desc).call();
            //推送到远程
            if(StringUtils.isBlank(gitVO.getGitUsername()) || StringUtils.isBlank(gitVO.getGitPassword())){
                git.push().setCredentialsProvider(provider).call();
            }else{
                git.push().call();
            }
//            LOG.info("Commit And Push file " + filePath + " to repository at " + git.getRepository().getDirectory());
        }catch (Exception e) {
            e.printStackTrace();
            commitAndPushFlag=false;
//            LOG.error("Commit And Push error! \n"+e.getMessage());
        }
        return commitAndPushFlag;

    }

    /**
     * 拉取远程代码
     * @return 远程分支名
     */
    public boolean pull(){
        return pull(gitVO.getBranchName());
    }

    /**
     * 拉取远程代码
     * @param remoteBranchName
     * @return 远程分支名
     */
    public boolean pull(String remoteBranchName){

        boolean pullFlag=true;
        try (Git git = Git.open( new File(gitVO.getLocalRepoGitConfig()) );) {
        	 UsernamePasswordCredentialsProvider provider =new UsernamePasswordCredentialsProvider(gitVO.getGitUsername(), gitVO.getGitPassword());
            git.pull()
                    .setRemoteBranchName(remoteBranchName)
        	        .setCredentialsProvider(provider)
                    .call();
        }catch (Exception e) {
            e.printStackTrace();
            pullFlag=false;
        }
        return pullFlag;
    }
    public boolean checkout(String branchName){

        boolean checkoutFlag=true;
        try (Git git = Git.open( new File(gitVO.getLocalRepoGitConfig()) );) {
            git.checkout().setName("refs/heads/"+branchName).setForce(true).call();
        }catch (Exception e) {
            e.printStackTrace();
            checkoutFlag=false;
        }
        return checkoutFlag;
    }
    public boolean checkout(){

        return checkout(gitVO.getBranchName());

    }
    /**
     *  从远程获取最新版本到本地   不会自动合并 merge
     * @return
     */
    public boolean fetch(){

        boolean fetchFlag=true;
        try (Git git = Git.open( new File(gitVO.getLocalRepoGitConfig()) );) {
            git.fetch().setCheckFetchedObjects(true).call();
        }catch (Exception e) {
            e.printStackTrace();
            fetchFlag=false;
        }
        return fetchFlag;
    }

    /**
     * 拉取新创建的分支到本地
     * @param cloneURL
     * @return
     */
    @SuppressWarnings("static-access")
    public boolean pullNewBranchToLocal(String cloneURL){
        boolean resultFlag = false;
        String[] splitURL = cloneURL.split(" ");
        String branchName = splitURL[1];
        String fileDir = gitVO.getInitLocalCodeDir()+"/"+branchName;
        //检查目标文件夹是否存在
        File file = new File(fileDir);
        if(file.exists()){
            deleteFolder(file);
        }
        Git git;
        try {
            git = Git.open( new File(gitVO.getLocalRepoGitConfig()) );
            git.cloneRepository().setURI(cloneURL).setDirectory(file).call();
            resultFlag = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return resultFlag;
    }


    private void deleteFolder(File file){
        if(file.isFile() || file.list().length==0){
            file.delete();
        }else{
            File[] files = file.listFiles();
            for(int i=0;i<files.length;i++){
                deleteFolder(files[i]);
                files[i].delete();
            }
        }
    }
    /**
     * 生成文件写内容
     * @param content 文件内容
     * @param filePath  文件名称
     */
    @SuppressWarnings("unused")
    private boolean createFile(String content,String filePath){

        //删除前一天临时目录
//	  File af = new File(filePath+File.separator+DateUtil.getAgoBackDate(-1));
//	  if (af.exists()) {
//		  deleteFolder(af);
//	  }
//	  //创建临时存储目录
//	  File f = new File(filePath+File.separator+DateUtil.getAgoBackDate(0));
//	  if (!f.exists()) {
//		f.mkdirs();
//	  }
//	  if (!fileName.endsWith(".sql")) {
//		  fileName+=".sql";
//	  }
        boolean createFileFlag=true;
        File file =new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                createFileFlag=false;
            }
        }
        try(BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8));) {
            bw.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            createFileFlag=false;
        } catch (IOException e) {
            e.printStackTrace();
            createFileFlag=false;
        }
        return createFileFlag;
    }
    /**
     * 创建本地新仓库
     * @param repoPath 仓库地址 D:/workspace/TestGitRepository
     * @return
     * @throws IOException
     */
    public Repository createNewRepository(String repoPath) throws IOException {
        File localPath = new File(repoPath);
        // create the directory
        Repository repository = FileRepositoryBuilder.create(new File(localPath, ".git"));
        repository.create();
        return repository;
    }
    /**
     * 创建仓库，仅需要执行一次
     */
    public boolean setupRepository(){
        boolean setupRepositoryFlag=true;
        try {
            //设置远程服务器上的用户名和密码
            UsernamePasswordCredentialsProvider provider =new UsernamePasswordCredentialsProvider(gitVO.getGitUsername(),gitVO.getGitPassword());
            if(StringUtils.isBlank(gitVO.getGitUsername()) || StringUtils.isBlank(gitVO.getGitPassword())){
                Git git =Git.cloneRepository().setURI(gitVO.getRemoteRepoUri()) //设置远程URI
                        .setBranch(gitVO.getBranchName())   //设置clone下来的分支,默认master
                        .setDirectory(new File(gitVO.getLocalRepoPath()))  //设置下载存放路径
                        .call();
            }else{
                Git git =Git.cloneRepository().setURI(gitVO.getRemoteRepoUri()) //设置远程URI
                        .setBranch(gitVO.getBranchName())   //设置clone下来的分支,默认master
                        .setDirectory(new File(gitVO.getLocalRepoPath()))  //设置下载存放路径
                        .setCredentialsProvider(provider) //设置权限验证
                        .call();
            }
        } catch (Exception e) {
            e.printStackTrace();
            setupRepositoryFlag=false;
        }
        return setupRepositoryFlag;
    }
}
