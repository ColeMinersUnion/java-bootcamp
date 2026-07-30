

| Question | Your answer                                    |
| -------- |------------------------------------------------|
| Which profile is active when you run plain `mvn package`? | `dev` by default                               |
| How do you activate `prod` on the command line? | `mvn -Pprod …` (example: `mvn -Pprod package`) |
| What is the `app.env` value under `dev`? | `dev`                                          |
| What is the `app.env` value under `prod`? | `prod`                                          |

Explain why each is dangerous:

- putting production database passwords inside the `dev` profile;

The `dev` profile is for development, and should not be able to affect the production environment, including production databases.

- making `prod` `activeByDefault` on every engineer laptop;

If `prod` was the default envinronment, then changes would be pushed directly to production. Bugs, exploits and other bad practices may not go through the testing required before being pushed to production.

- assuming profiles change Java package names (they do not — they change build/config properties);

This assumption could cause developers to faultily change package names, or reveal secrets in the package names.

- documenting secrets in screenshots of profile properties.

Secrets need to be secure. If they exist as screenshots 

```markdown
Keep `dev` as the laptop default.
Activate `prod` intentionally with `-Pprod`.
Never store real production secrets in `pom.xml` profiles.
```